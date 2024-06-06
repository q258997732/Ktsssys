package com.bob.ktssts.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BeanInspector implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	//@Bean
	public void inspectBeans() {
		// 获取所有bean的名称
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			// 获取bean的类型
			Class beanType = applicationContext.getType(beanName);
			// 获取bean的别名（如果有的话）
			String[] aliases = applicationContext.getAliases(beanName);

			System.out.println("Bean name: " + beanName);
			if (beanType != null) {
//				System.out.println("Bean type: " + beanType.getName());
			}
			if (aliases != null && aliases.length > 0) {
				System.out.println("Bean aliases: " + Arrays.toString(aliases));
			}
			// 打印其他bean的元数据...
		}
	}
}